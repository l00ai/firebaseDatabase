package com.example.firebasedatabase

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasedatabase.Adapter.ContactAdapter
import com.example.firebasedatabase.Models.Contact
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var progressDialog :ProgressDialog? = null
    val contacts :ArrayList<Contact> = ArrayList()
    var contact :Map<String, Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        showProgressDialog()


        val dbInstant = FirebaseFirestore.getInstance()


        fab.setOnClickListener {
            val intent = Intent(this,AddContactActivity::class.java)
            startActivity(intent)
        }

        dbInstant.collection("contacts")
            .get()
            .addOnCompleteListener {result->
                if(result.isSuccessful){
                    for( document in result.result!!){
                        contact = document.data
                        contacts.add(Contact(contact!!["name"].toString(),contact!!["phone"].toString(),contact!!["address"].toString()))
                    }
                    hideProgressDialog()

                    val contactAdapter = ContactAdapter(this, contacts)
                    rv!!.adapter = contactAdapter
                }

            }



    }


    private fun showProgressDialog(){
        if(progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.setMessage("Loading....")
            progressDialog!!.setCancelable(false)
        }
        progressDialog!!.show()
    }

    private fun hideProgressDialog(){
        if(progressDialog!!.isShowing){
            progressDialog!!.dismiss()
        }
    }
}