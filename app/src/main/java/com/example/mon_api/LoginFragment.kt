package com.example.mon_api


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var btn: Button
    private lateinit var view: View
    private val PASSWORD1 = "1234567890"
    private val PASSWORD2 = "0987654321"
    private val PASSWORD3 = "1234509876"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false)
        user = view.findViewById(R.id.username)
        pass = view.findViewById(R.id.password)
        btn = view.findViewById(R.id.login)

        btn.setOnClickListener {
            val isCorrect = validate(user.text.toString(), pass.text.toString())
            if (isCorrect) {
                val fragmentManager = activity?.supportFragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.frameLayout, RecyclerFragment())
                fragmentTransaction?.commit()
                Toast.makeText(view.context, "Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "Hey,Password does not match", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }

    private fun validate(user: String, pass: String): Boolean {
        val storedPasswords = listOf(PASSWORD1, PASSWORD2, PASSWORD3)
        if (user.length != 10) {
            Toast.makeText(view.context, "username contains exactly 10 Numbers", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        // validation
        try {
            val data = user.toLong()
        } catch (e: NumberFormatException) {
            Toast.makeText(view.context, "Username contains only Numbers", Toast.LENGTH_SHORT).show()
            return false
        }

        // password
        if (pass.length != 10) {
            Toast.makeText(view.context, "Password contains exactly 10 Numbers", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        try {
            val data = pass.toLong()
        } catch (e: NumberFormatException) {
            Toast.makeText(view.context, "Enter only numbers in password", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (user != pass) {
            Toast.makeText(view.context, "Username and password not match", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return storedPasswords.contains(pass)
    }
}
