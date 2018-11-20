package kz.batana.beautysoft.camera


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.common.util.UriUtil
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_camera.*

import kz.batana.beautysoft.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraFragment : Fragment() {

    val CAMERA_REQUEST_CODE = 0
    lateinit var imageFilePath: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgAvatar.setOnClickListener{
            Dexter.withActivity(activity)
                    .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .withListener(object : MultiplePermissionsListener {
                        override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                            AlertDialog.Builder(activity as Context)
                                    .setTitle("getString(R.string.storage_permission_rationale_title)")
                                    .setMessage("getString(R.string.storage_permission_rationale_message)")
                                    .setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener {
                                        dialogInterface, i ->
                                        dialogInterface.dismiss()
                                        token?.cancelPermissionRequest()
                                    })
                                    .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener {
                                        dialogInterface, i ->
                                        dialogInterface.dismiss()
                                        token?.continuePermissionRequest()
                                    })
                                    .show()
                        }

                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                            if (report?.areAllPermissionsGranted()!!) {

                                try {
                                    val imageFile = createImageFile()
                                    val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                    if(callCameraIntent.resolveActivity(activity!!.packageManager) != null) {
                                        val authorities = activity!!.packageName + ".fileprovider"
                                        val imageUri = FileProvider.getUriForFile(activity as Context, authorities, imageFile)
                                        callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                                        startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
                                    }
                                } catch (e: IOException) {
                                    Toast.makeText(activity, "Could not create file!", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }

                    }
                    ).check()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
/*                if(resultCode == Activity.RESULT_OK && data != null) {
                    photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)
                }*/
                if (resultCode == Activity.RESULT_OK) {
                    val imgUri = Uri.Builder()
                            .scheme(UriUtil.LOCAL_FILE_SCHEME)
                            .path(imageFilePath)
                            .build()
                    imgAvatar.setImageURI(imgUri, this)
                }
            }
            else -> {
                Toast.makeText(activity, "Unrecognized request code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName: String = "JPEG_" + timeStamp + "_"
        val storageDir: File = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if(!storageDir.exists()) storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        return imageFile
    }

}
