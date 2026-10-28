package com.d1cr4m.contas;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONObject;

public class MainActivity extends Activity {
    static final int REQ_EXP = 11, REQ_IMP = 12;
    WebView w;
    String pendente;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        Lembrete.canal(this);
        Lembrete.agendar(this);
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
        }
        w = new WebView(this);
        WebSettings s = w.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        w.setWebChromeClient(new WebChromeClient());
        w.setWebViewClient(new WebViewClient());
        w.addJavascriptInterface(new Ponte(this), "Android");
        setContentView(w);
        w.loadUrl("file:///android_asset/index.html");
    }

    void exportar(String json) {
        pendente = json;
        Intent i = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("application/json");
        i.putExtra(Intent.EXTRA_TITLE, "contas-backup.json");
        startActivityForResult(i, REQ_EXP);
    }

    void importar() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(i, REQ_IMP);
    }

    @Override
    protected void onActivityResult(int rq, int rs, Intent d) {
        if (rs != RESULT_OK || d == null || d.getData() == null) return;
        Uri u = d.getData();
        try {
            if (rq == REQ_EXP && pendente != null) {
                OutputStream o = getContentResolver().openOutputStream(u);
                o.write(pendente.getBytes("UTF-8"));
                o.close();
                Toast.makeText(this, "Backup salvo", Toast.LENGTH_LONG).show();
            } else if (rq == REQ_IMP) {
                InputStream in = getContentResolver().openInputStream(u);
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                int n;
                while ((n = in.read(buf)) > 0) bo.write(buf, 0, n);
                in.close();
                String t = bo.toString("UTF-8");
                w.evaluateJavascript("importarDados(" + JSONObject.quote(t) + ")", null);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
