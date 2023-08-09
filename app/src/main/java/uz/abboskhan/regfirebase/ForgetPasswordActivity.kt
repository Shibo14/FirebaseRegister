package uz.abboskhan.regfirebase

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import uz.abboskhan.regfirebase.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var pb: Dialog
    lateinit var binding: ActivityForgetPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding?.btnForgotPasswordSubmit?.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        val email = binding?.etForgotPasswordEmail?.text.toString()
        if (validateEmail(email)) {
            showProgressBar()
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding?.tilEmailForgetPassword?.visibility = View.GONE
                    binding?.tvSubmitMsg?.visibility = View.VISIBLE
                    binding?.btnForgotPasswordSubmit?.visibility = View.GONE
                    hideProgressBar()
                } else {
                    hideProgressBar()
                    Toast.makeText(this, "Reset password failed, try again latter",Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun validateEmail(email: String): Boolean {
        return if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding?.tilEmailForgetPassword?.error = "Enter valid email address"
            false
        } else {
            binding?.tilEmailForgetPassword?.error = null
            true
        }
    }
    fun showProgressBar() {
        pb = Dialog(this)
        pb.setContentView(R.layout.progress_bar)
        pb.setCancelable(false)
        pb.show()
    }

    fun hideProgressBar() {
        pb.dismiss()
    }
}
