package com.example.loginsignupauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupauth.databinding.ActivitySignUpBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var  firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth=FirebaseAuth.getInstance()

        binding.signbutton.setOnClickListener{
            val email=binding.signupmail.text.toString()
            val password=binding.signuppassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this) {task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"SignUp Successful",Toast.LENGTH_SHORT).show()
                            val intent=Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"SignUp UnsSuccessful",Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"Enter The Email And Password Please",Toast.LENGTH_SHORT).show()
            }
        }
            binding.logintext.setOnClickListener {
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
    }
}