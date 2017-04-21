package miguelparedez.imei;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class MainActivity extends Activity {
    private WebView webview;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
    private LinearLayout linearLayout;
    private ImageView footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.webview = (WebView) findViewById(R.id.webview);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        linearLayout = (LinearLayout) findViewById(R.id.cargando_base);
        footer = (ImageView) findViewById(R.id.imageView2);
        progressBar = ProgressDialog.show(MainActivity.this, "", "Actualizando Base de Datos...");

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                webview.loadUrl("javascript:(function() { " +
                        "$('h2').hide();" +
                        " $('p').hide();" +
                        "document.getElementsByTagName('header')[0].style.display='none'; " +
                        "document.getElementsByTagName('footer')[0].style.display='none'; " +
                        "document.body.getElementsByClassName('encabezadoimei clearfix')[0].style.display='none'; " +
                        "document.body.getElementsByClassName('cuerpoimeipreguntas clearfix')[0].style.display='none'; " +
                        "document.body.getElementsByClassName('col-md-2 col-sm-12 col-xs-12')[0].style.display='none'; " +
                        "document.body.getElementsByClassName('col-md-3 col-sm-12 col-xs-12')[0].style.display='none'; " +
                        "document.body.getElementsByClassName('col-md-5 col-sm-12 col-xs-12')[0].style.display='none'; " +
                        "})()");
                Log.i(TAG, "Finished loading URL: " + url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                    linearLayout.setVisibility(View.GONE);
                    webview.setVisibility(View.VISIBLE);
                    footer.setVisibility(View.VISIBLE);
                }
            }
        });
        webview.loadUrl("http://enacom.gob.ar/imei");
    }
}
