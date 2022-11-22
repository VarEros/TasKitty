package ni.edu.uca.taskitty

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import ni.edu.uca.taskitty.databinding.ActivityMainBinding

class SplashScreen : AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_TasKitty_Splash)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },500)

    }

}