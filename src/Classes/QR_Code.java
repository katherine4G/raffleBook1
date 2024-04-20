
package Classes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
/**
 *
 * @author kathe
 */
public class QR_Code {
     
     public static BitMatrix generateQRCode(String content, int width, int height) throws WriterException {
         QRCodeWriter qrCodeWriter = new QRCodeWriter();
         return qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
    }
}
