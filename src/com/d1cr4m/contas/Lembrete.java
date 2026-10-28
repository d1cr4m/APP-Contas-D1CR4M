package com.d1cr4m.contas;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class Lembrete {
    static final String CH = "contas";
    static final int FL = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;

    static void canal(Context c) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(new NotificationChannel(CH, "Lembretes de contas", NotificationManager.IMPORTANCE_DEFAULT));
        }
    }

    static void agendar(Context c) {
        AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(c, 1, new Intent(c, AlarmeReceiver.class), FL);
        Calendar t = Calendar.getInstance();
        SharedPreferences p = c.getSharedPreferences("lembretes", 0);
        t.set(Calendar.HOUR_OF_DAY, p.getInt("h", 9));
        t.set(Calendar.MINUTE, p.getInt("m", 0));
        t.set(Calendar.SECOND, 0);
        if (t.before(Calendar.getInstance())) t.add(Calendar.DAY_OF_YEAR, 1);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }

    static void verificar(Context c) {
        try {
            JSONArray a = new JSONArray(c.getSharedPreferences("lembretes", 0).getString("pend", "[]"));
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Calendar cal = Calendar.getInstance();
            String hoje = f.format(cal.getTime());
            cal.add(Calendar.DAY_OF_YEAR, 1);
            String amanha = f.format(cal.getTime());
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            StringBuilder h = new StringBuilder(), at = new StringBuilder(), am = new StringBuilder();
            int n = 0;
            for (int i = 0; i < a.length(); i++) {
                JSONObject o = a.getJSONObject(i);
                String d = o.optString("d", "");
                if (d.length() == 0) continue;
                String l = o.getString("n") + " (" + nf.format(o.getDouble("v")) + ")\n";
                int k = d.compareTo(hoje);
                if (k == 0) { h.append(l); n++; }
                else if (k < 0) { at.append(l); n++; }
                else if (d.equals(amanha)) { am.append(l); n++; }
            }
            if (n == 0) return;
            StringBuilder t = new StringBuilder();
            if (h.length() > 0) t.append("Vence hoje:\n").append(h);
            if (at.length() > 0) t.append("Atrasadas:\n").append(at);
            if (am.length() > 0) t.append("Vence amanhã:\n").append(am);
            String texto = t.toString().trim();
            PendingIntent pi = PendingIntent.getActivity(c, 0, new Intent(c, MainActivity.class), FL);
            Notification.Builder nb = Build.VERSION.SDK_INT >= 26
                ? new Notification.Builder(c, CH) : new Notification.Builder(c);
            nb.setSmallIcon(c.getApplicationInfo().icon)
              .setContentTitle(n == 1 ? "1 conta para pagar" : n + " contas para pagar")
              .setContentText(texto.replace("\n", " · "))
              .setStyle(new Notification.BigTextStyle().bigText(texto))
              .setContentIntent(pi)
              .setAutoCancel(true);
            ((NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, nb.build());
        } catch (Exception e) {
        }
    }
}
