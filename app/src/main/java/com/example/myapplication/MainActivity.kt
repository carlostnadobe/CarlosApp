package com.example.myapplication
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.adobe.marketing.mobile.*
import com.adobe.marketing.mobile.Target
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.action_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        try {
            MobileCore.setApplication(application)
            MobileCore.setLogLevel(LoggingMode.DEBUG)
            Target.registerExtension()
            Griffon.registerExtension()
            MobileServices.registerExtension()
            Analytics.registerExtension()
            Identity.registerExtension()
            Lifecycle.registerExtension()
            Signal.registerExtension()
            UserProfile.registerExtension()
            MobileCore.start { MobileCore.configureWithAppID("2491ed4c983b/ae44614d1ed3/launch-fbf447265b73-development") }
        } catch (e: Exception){}
         System.out.println("Oops!")

        val additionalContextData: MutableMap<String, String> =
            HashMap()
        additionalContextData["screenName"] = "contextdata_home"
        MobileCore.trackState("homePage", additionalContextData)
        MobileCore.trackAction("onCreate function", additionalContextData);
        System.out.println("trackAction onCreate!")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        MobileCore.setApplication(application)
        MobileCore.lifecycleStart(null)

        val additionalContextData: MutableMap<String, String> =
            HashMap()
        additionalContextData["screenName"] = "contextdata_home"
        MobileCore.trackState("homePage", additionalContextData)
        MobileCore.trackAction("onResume function", additionalContextData);
        System.out.println("trackAction onResume!")
    }

    override fun onPause() {
        super.onPause()
        MobileCore.lifecyclePause()
        val additionalContextData: MutableMap<String, String> =
            HashMap()
        additionalContextData["screenName"] = "contextdata_home"
        MobileCore.trackState("homePage", additionalContextData)
        MobileCore.trackAction("onPause function", additionalContextData);
        System.out.println("trackAction onPause!")
    }
}