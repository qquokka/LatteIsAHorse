package com.latte.service;

import com.latte.dto.QRCode;

public interface IQRCodeService {
	int addQRCode(QRCode qrcode);
	
	int deleteQRCode(QRCode qrcode);
}
