package com.d1cr4m.contas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context c, Intent i) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(i.getAction())) {
            Lembrete.canal(c);
            Lembrete.agendar(c);
        } else {
            Lembrete.verificar(c);
        }
    }
}
