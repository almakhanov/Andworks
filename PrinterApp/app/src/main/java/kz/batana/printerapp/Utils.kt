package kz.batana.printerapp

import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.Config
import android.text.format.Time
import android.util.Log
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlin.experimental.or


class Utils {

    // ��ͼƬ���ˮӡ
    private fun createBitmap(src: Bitmap): Bitmap {
        val t = Time()
        t.setToNow()
        val w = src.width
        val h = src.height
        val mstrTitle = t.year.toString() + " �� " + (t.month + 1) + " �� " + t.monthDay + " ��"
        val bmpTemp = Bitmap.createBitmap(w, h, Config.ARGB_8888)
        val canvas = Canvas(bmpTemp)
        val p = Paint()
        val familyName = "����"
        val font = Typeface.create(familyName, Typeface.BOLD)
        p.color = Color.BLACK
        p.typeface = font
        p.textSize = 33f
        canvas.drawBitmap(src, 0f, 0f, p)
        canvas.drawText(mstrTitle, 20f, 310f, p)
        canvas.save()
        canvas.restore()
        return bmpTemp
    }

    companion object {

        // UNICODE 0x23 = #
        val UNICODE_TEXT = byteArrayOf(0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23, 0x23)

        private val hexStr = "0123456789ABCDEF"
        private val binaryArray = arrayOf("0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111")

        fun decodeBitmap(bmp: Bitmap): ByteArray? {
            val bmpWidth = bmp.width
            val bmpHeight = bmp.height

            val list = ArrayList<String>() //binaryString list
            var sb: StringBuffer


            var bitLen = bmpWidth / 8
            val zeroCount = bmpWidth % 8

            var zeroStr = ""
            if (zeroCount > 0) {
                bitLen = bmpWidth / 8 + 1
                for (i in 0 until 8 - zeroCount) {
                    zeroStr = zeroStr + "0"
                }
            }

            for (i in 0 until bmpHeight) {
                sb = StringBuffer()
                for (j in 0 until bmpWidth) {
                    val color = bmp.getPixel(j, i)

                    val r = color shr 16 and 0xff
                    val g = color shr 8 and 0xff
                    val b = color and 0xff

                    // if color close to white，bit='0', else bit='1'
                    if (r > 160 && g > 160 && b > 160)
                        sb.append("0")
                    else
                        sb.append("1")
                }
                if (zeroCount > 0) {
                    sb.append(zeroStr)
                }
                list.add(sb.toString())
            }

            val bmpHexList = binaryListToHexStringList(list)
            val commandHexString = "1D763000"
            var widthHexString = Integer
                    .toHexString(if (bmpWidth % 8 == 0)
                        bmpWidth / 8
                    else
                        bmpWidth / 8 + 1)
            if (widthHexString.length > 2) {
                Log.e("decodeBitmap error", " width is too large")
                return null
            } else if (widthHexString.length == 1) {
                widthHexString = "0$widthHexString"
            }
            widthHexString = widthHexString + "00"

            var heightHexString = Integer.toHexString(bmpHeight)
            if (heightHexString.length > 2) {
                Log.e("decodeBitmap error", " height is too large")
                return null
            } else if (heightHexString.length == 1) {
                heightHexString = "0$heightHexString"
            }
            heightHexString = heightHexString + "00"

            val commandList = ArrayList<String>()
            commandList.add(commandHexString + widthHexString + heightHexString)
            commandList.addAll(bmpHexList)

            return hexList2Byte(commandList)
        }

        fun binaryListToHexStringList(list: List<String>): List<String> {
            val hexList = ArrayList<String>()
            for (binaryStr in list) {
                val sb = StringBuffer()
                var i = 0
                while (i < binaryStr.length) {
                    val str = binaryStr.substring(i, i + 8)

                    val hexString = myBinaryStrToHexString(str)
                    sb.append(hexString)
                    i += 8
                }
                hexList.add(sb.toString())
            }
            return hexList

        }

        fun myBinaryStrToHexString(binaryStr: String): String {
            var hex = ""
            val f4 = binaryStr.substring(0, 4)
            val b4 = binaryStr.substring(4, 8)
            for (i in 0 until binaryArray.size) {
                if (f4 == binaryArray[i])
                    hex += hexStr.substring(i, i + 1)
            }
            for (i in 0 until binaryArray.size) {
                if (b4 == binaryArray[i])
                    hex += hexStr.substring(i, i + 1)
            }

            return hex
        }

        fun hexList2Byte(list: List<String>): ByteArray {
            val commandList = ArrayList<ByteArray>()

            for (hexStr in list) {
                hexStringToBytes(hexStr)?.let { commandList.add(it) }
            }
            return sysCopy(commandList)
        }

        fun hexStringToBytes(hexString: String?): ByteArray? {
            var hexString = hexString
            if (hexString == null || hexString == "") {
                return null
            }
            hexString = hexString.toUpperCase()
            val length = hexString.length / 2
            val hexChars = hexString.toCharArray()
            val d = ByteArray(length)
            for (i in 0 until length) {
                val pos = i * 2
                d[i] = ((charToByte(hexChars[pos]).toInt() shl 4).toByte() or charToByte(hexChars[pos + 1]))
            }
            return d
        }

        fun sysCopy(srcArrays: List<ByteArray>): ByteArray {
            var len = 0
            for (srcArray in srcArrays) {
                len += srcArray.size
            }
            val destArray = ByteArray(len)
            var destLen = 0
            for (srcArray in srcArrays) {
                System.arraycopy(srcArray, 0, destArray, destLen, srcArray.size)
                destLen += srcArray.size
            }
            return destArray
        }

        private fun charToByte(c: Char): Byte {
            return "0123456789ABCDEF".indexOf(c).toByte()
        }

        /**
         * ��ʾ��Ϣ��
         */
        fun ShowMessage(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }


        fun Bytes2Bimap(b: ByteArray): Bitmap? {
            return if (b.size != 0) {
                BitmapFactory.decodeByteArray(b, 0, b.size)
            } else {
                null
            }
        }

        /**����ͼƬ ��ȡ��ӡ���� */
        fun getReadBitMapBytes(bitmap: Bitmap): ByteArray? {
            /***ͼƬ���ˮӡ */
            //bitmap = createBitmap(bitmap);
            var bytes: ByteArray? = null  //��ӡ����
            val width = bitmap.width
            val height = bitmap.height
            println("width=$width, height=$height")
            val heightbyte = (height - 1) / 8 + 1
            val bufsize = width * heightbyte
            var m1: Int
            var n1: Int
            val maparray = ByteArray(bufsize)
            val rgb = ByteArray(3)
            val pixels = IntArray(width * height) //ͨ��λͼ�Ĵ�С�������ص�����

            bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
            /**����ͼƬ ��ȡλͼ���� */
            for (j in 0 until height) {
                for (i in 0 until width) {
                    val pixel = pixels[width * j + i]
                    /**��ȡ�ңǣ�ֵ */
                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)
                    //System.out.println("i=" + i + ",j=" + j + ":(" + r + ","+ g+ "," + b + ")");
                    rgb[0] = r.toByte()
                    rgb[1] = g.toByte()
                    rgb[2] = b.toByte()
                    if (r != 255 || g != 255 || b != 255) {//������ǿհ׵Ļ��ú�ɫ���    �������ͯЬҪ������ɫ�����ﴦ��
                        m1 = j / 8 * width + i
                        n1 = j - j / 8 * 8
                        maparray[m1] = maparray[m1] or (1 shl 7 - n1.toByte()).toByte()
                    }
                }
            }
            var b = ByteArray(322)
            var line = 0
            var j = 0
            val baos = ByteArrayOutputStream()

            /**��λͼ���ݽ��д��� */
            for (i in maparray.indices) {
                b[j] = maparray[i]
                j++
                if (j == 322) {
                    /**  322ͼƬ�Ŀ�  */
                    if (line < (322 - 1) / 8) {
                        val lineByte = ByteArray(329)
                        val nL = 322.toByte()
                        val nH = (322 shr 8).toByte()
                        val index = 5
                        /**��Ӵ�ӡͼƬǰ���ַ�  ÿ�е� ������8λ */
                        lineByte[0] = 0x1B
                        lineByte[1] = 0x2A
                        lineByte[2] = 1
                        lineByte[3] = nL
                        lineByte[4] = nH
                        /**copy �������� */
                        System.arraycopy(b, 0, lineByte, index, b.size)

                        lineByte[lineByte.size - 2] = 0x0D
                        lineByte[lineByte.size - 1] = 0x0A
                        baos.write(lineByte, 0, lineByte.size)
                        try {
                            baos.flush()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        line++
                    }
                    j = 0
                }
            }
            bytes = baos.toByteArray()
            return bytes
        }

        /**
         * �ַ����ָ�,ͨ��ָ���ķ��ŷָ��ַ���
         */

    }




}

