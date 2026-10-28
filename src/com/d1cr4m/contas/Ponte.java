package com.d1cr4m.contas;

import android.webkit.JavascriptInterface;

public class Ponte {
    private final MainActivity a;
    Ponte(MainActivity a) { this.a = a; }

    @JavascriptInterface
    public void sync(String json) {
        a.getSharedPreferences("lembretes", 0).edit().putString("pend", json).apply();
    }

    @JavascriptInterface
    public void hora(int h, int m) {
        a.getSharedPreferences("lembretes", 0).edit().putInt("h", h).putInt("m", m).apply();
        Lembrete.agendar(a);
    }

    @JavascriptInterface
    public void exportar(final String json) {
        a.runOnUiThread(new Runnable() { public void run() { a.exportar(json); } });
    }

    @JavascriptInterface
    public void importar() {
        a.runOnUiThread(new Runnable() { public void run() { a.importar(); } });
    }
}
