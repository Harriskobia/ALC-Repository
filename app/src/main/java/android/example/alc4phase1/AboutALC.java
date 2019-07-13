package android.example.alc4phase1;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutALC extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

       mWebView = (WebView) findViewById(R.id.alc_page_web_view);
       mWebView.setWebViewClient(new SSLTolerentWebViewClient());
       mWebView.getSettings().setJavaScriptEnabled(true);
       mWebView.loadUrl("https://andela.com/alc/");


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return  true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
    public class SSLTolerentWebViewClient extends WebViewClient {
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
            switch (error.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    handler.proceed();
                    break;
                case SslError.SSL_EXPIRED:
                    handler.proceed();
                    break;
                case SslError.SSL_IDMISMATCH:
                    handler.proceed();
                    break;
                case SslError.SSL_NOTYETVALID:
                    handler.proceed();
                    break;
            }
        }
    }

}
