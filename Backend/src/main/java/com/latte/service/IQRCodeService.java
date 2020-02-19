package com.latte.service;

import com.latte.dto.QRCode;

public interface IQRCodeService {
	//QR 코드 DB에 등록
	int addQRCode(QRCode qrcode);

	//DB에 등록된 QR 코드 삭제
	int deleteQRCode(QRCode qrcode);

	//DB에 해당 QR 코드가 등록되어 있는지 여부
	int isExist(String encryptedCode);
}
