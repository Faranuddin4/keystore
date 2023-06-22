package com.aksa.encry_decry_sample

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aksa.encry_decry_sample.en_de_cryptor.DeCryptor
import com.aksa.encry_decry_sample.en_de_cryptor.EnCryptor
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException

private const val SAMPLE_ALIAS = "MYALIAS"
const val TAG = "decryption"
const val TAG_Enc = "encryption"

class MainActivity : AppCompatActivity() {
    var encryptor: EnCryptor? = null
    var decryptor: DeCryptor? = null
    private lateinit var txt_encrypt : TextView
    private lateinit var txt_decrypt : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt_encrypt =   findViewById(R.id.txt_encrypt)
        txt_decrypt =   findViewById(R.id.txt_decrypt)
        encryptor = EnCryptor()
        try {
            decryptor = DeCryptor()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        encryptText()
        decryptText()
    }


    private fun encryptText() {
        try {

            val encryptedText: ByteArray = encryptor?.encryptText(SAMPLE_ALIAS, txt_encrypt.text.toString())!!
            val a = Base64.encodeToString(encryptedText, Base64.DEFAULT)
            txt_encrypt.text = a
            Log.e(TAG_Enc, "encryptText: $a")
        } catch (e: UnrecoverableEntryException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e(TAG_Enc, "onClick() called with: " + e.message, e)
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: SignatureException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }
    }

    private fun decryptText() {
        try {
          val deTxt=  decryptor?.decryptData(SAMPLE_ALIAS, encryptor?.encryption, encryptor?.iv)
            Log.e(TAG, "decryptText: $deTxt", )
            txt_decrypt.text = deTxt
        } catch (e: UnrecoverableEntryException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }
    }
}
