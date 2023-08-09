package uz.abboskhan.regfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import uz.abboskhan.regfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        binding.logoutBtn.setOnClickListener {

            if (auth.currentUser != null) {
                auth.signOut()
                startActivity(Intent(this, SignInActivity::class.java))
                finish()

            }


        }

    }
}