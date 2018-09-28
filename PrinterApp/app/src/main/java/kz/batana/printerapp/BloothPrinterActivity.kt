package kz.batana.printerapp


import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver
import kotlinx.android.synthetic.main.main.*




class BloothPrinterActivity : Activity() {

    val baseImage = "data:image/jpeg;base64,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"

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


    var SelectedBDAddress: String? = null
    private var mBtnConnetBluetoothDevice: Button? = null
    private var mBtnQuit: Button? = null
    private var mBtnPrint: Button? = null
    private var mBtnPrintOption: Button? = null
    private var mBtnTest: Button? = null
    private var mPrintContent: EditText? = null
    private var mBeiKuan: CheckBox? = null
    private var mUnderline: CheckBox? = null
    private var mBold: CheckBox? = null
    private var mBeiGao: CheckBox? = null
    private var mMinifont: CheckBox? = null
    private var mHightlight: CheckBox? = null


    internal var mBtnQuitOnClickListener: OnClickListener = OnClickListener {
        //�˳�ǰ�ر�����
        BluetoothPrintDriver.close()
        finish()
    }

    internal var mBtnConnetBluetoothDeviceOnClickListener: OnClickListener = object : OnClickListener {
        internal var serverIntent: Intent? = null
        override fun onClick(arg0: View) {
            // Launch the DeviceListActivity to see devices and do scan
            serverIntent = Intent(this@BloothPrinterActivity, DeviceListActivity::class.java)
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE)
        }
    }

    internal var mBtnPrintOnClickListener: OnClickListener = OnClickListener {
        if (BluetoothPrintDriver.IsNoConnection()) {
            return@OnClickListener
        }
        BluetoothPrintDriver.Begin()
        if (mBeiKuan!!.isChecked) {
            BluetoothPrintDriver.SetZoom(0x10.toByte())
        }
        if (mBeiGao!!.isChecked) {
            BluetoothPrintDriver.SetZoom(0x01.toByte())
        }
        if (mUnderline!!.isChecked) {
            BluetoothPrintDriver.SetUnderline(0x02.toByte())//�»���
        }
        if (mBold!!.isChecked) {
            BluetoothPrintDriver.AddBold(0x01.toByte())//����
        }
        if (mMinifont!!.isChecked) {
            BluetoothPrintDriver.SetCharacterFont(0x01.toByte())
        }
        if (mHightlight!!.isChecked) {
            BluetoothPrintDriver.AddInverse(0x01.toByte())
        }
        val tmpContent = mPrintContent!!.text.toString()
        BluetoothPrintDriver.ImportData(tmpContent)
        BluetoothPrintDriver.ImportData("\r")
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
    }

    internal var mBtnPrintOptionOnClickListener: OnClickListener = OnClickListener {
        val intent = Intent()
        intent.setClass(this@BloothPrinterActivity, PrinterOptionActivity::class.java)
        //intent.putExtra("mBloothPrinter", mBloothPrinter);
        startActivity(intent)
    }
    internal var mBtnTestOnClickListener: OnClickListener = OnClickListener {
        if (BluetoothPrintDriver.IsNoConnection()) {
            return@OnClickListener
        }
        BluetoothPrintDriver.Begin()
        BluetoothPrintDriver.ImportData(byteArrayOf(0x12, 0x54), 2)    //��ӡ�Բ�ҳ
        BluetoothPrintDriver.excute()
        BluetoothPrintDriver.ClearData()
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setTitle(R.string.bluetooth_unconnected)
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        SelectedBDAddress = ""
        if (!myBluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, 2)
        }

        val base64Image = baseImage.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

        val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        imageView.setImageBitmap(decodedByte)


        InitUIControl()
        //        mBloothPrinter = BloothPrinter.getInstance();
    }


    private fun InitUIControl() {
        mBtnQuit = findViewById<View>(R.id.btn_quit) as Button
        mBtnQuit!!.setOnClickListener(mBtnQuitOnClickListener)
        mBtnConnetBluetoothDevice = findViewById<View>(R.id.btn_connect_bluetooth_device) as Button
        mBtnConnetBluetoothDevice!!.setOnClickListener(mBtnConnetBluetoothDeviceOnClickListener)
        mBtnPrint = findViewById<View>(R.id.btn_print) as Button
        mBtnPrint!!.setOnClickListener(mBtnPrintOnClickListener)
        mBtnPrintOption = findViewById<View>(R.id.btn_option) as Button
        mBtnPrintOption!!.setOnClickListener(mBtnPrintOptionOnClickListener)
        mBtnTest = findViewById<View>(R.id.btn_test) as Button
        mBtnTest!!.setOnClickListener(mBtnTestOnClickListener)
        mPrintContent = findViewById<View>(R.id.edt_print_content) as EditText
        mBeiKuan = findViewById<View>(R.id.checkbox_beikuan) as CheckBox
        mUnderline = findViewById<View>(R.id.checkbox_underline) as CheckBox
        mBold = findViewById<View>(R.id.checkbox_bold) as CheckBox
        mBeiGao = findViewById<View>(R.id.checkbox_beigao) as CheckBox
        mMinifont = findViewById<View>(R.id.checkbox_minifont) as CheckBox
        mHightlight = findViewById<View>(R.id.checkbox_hightlight) as CheckBox
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        //�˳�ǰ�ر�����
        BluetoothPrintDriver.close()
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            REQUEST_CONNECT_DEVICE_SECURE ->
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // ������һ���豸֮ǰ�ȹر��������������豸֮���л�ʱ�����
                    BluetoothPrintDriver.close()
                    // ��ȡ�豸 MAC address
                    SelectedBDAddress = data.extras!!.getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS)
                    // ��������
                    if (!BluetoothPrintDriver.OpenPrinter(SelectedBDAddress)) {
                        BluetoothPrintDriver.close()
                        this@BloothPrinterActivity.setTitle(R.string.bluetooth_connect_fail)
                        //	            		mTitle.setText("����ʧ��");
                        return
                    } else {
                        // ���ӳɹ�����ʾ�豸��MAC��ַ
                        //	            		mTitle.setText(SelectedBDAddress);
                        val bluetoothConnectSucess = this@BloothPrinterActivity.resources.getString(R.string.bluetooth_connect_sucess)
                        this@BloothPrinterActivity.title = bluetoothConnectSucess + SelectedBDAddress!!
                    }
                }
            REQUEST_CONNECT_DEVICE_INSECURE ->
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                }//                connectDevice(data, false);
        }

        //		 //���Ը��ݶ���������������Ӧ�Ĳ���
        //		 if(10 == resultCode)  {
        //			 if(GlobalVariable.gOutputStream != null){
        //				 String bluetoothDeviceName = data.getExtras().getString("bluetooth_devicename");
        //				 String bluetoothConnectSucess = BloothPrinterActivity.this.getResources().getString(R.string.bluetooth_connect_sucess);
        //				 BloothPrinterActivity.this.setTitle(bluetoothConnectSucess+bluetoothDeviceName);
        //				 mBloothPrinter.setOutputStream(GlobalVariable.gOutputStream);
        //				 //mBloothPrinter.Begin();
        //			 }
        //		 }
        //		 if(20 == resultCode)  {
        //			 BloothPrinterActivity.this.setTitle(R.string.bluetooth_connect_fail);
        //			 mBloothPrinter.setOutputStream(null);
        //		 }
        //		 if(30 == resultCode)  {
        //			 BloothPrinterActivity.this.setTitle(R.string.bluetooth_connected_cannot_connect);
        //		 }
        //		 if(GlobalVariable.gOutputStream == null){
        //			 BloothPrinterActivity.this.setTitle(R.string.bluetooth_connect_fail);
        //		 }
        //		 super.onActivityResult(requestCode, resultCode, data);
    }

    companion object {
        /** Called when the activity is first created.  */
        lateinit var myBluetoothAdapter: BluetoothAdapter
        //	public static BloothPrinter mBloothPrinter = null;

        // Message types sent from the BluetoothChatService Handler
        val MESSAGE_STATE_CHANGE = 1
        val MESSAGE_READ = 2
        val MESSAGE_WRITE = 3
        val MESSAGE_DEVICE_NAME = 4
        val MESSAGE_TOAST = 5

        // Key names received from the BluetoothChatService Handler
        val DEVICE_NAME = "device_name"
        val TOAST = "toast"

        // Intent request codes
        private val REQUEST_CONNECT_DEVICE_SECURE = 1
        private val REQUEST_CONNECT_DEVICE_INSECURE = 2
    }
}