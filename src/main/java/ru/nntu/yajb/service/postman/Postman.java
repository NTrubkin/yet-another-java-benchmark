package ru.nntu.yajb.service.postman;

import ru.nntu.yajb.model.DataPackage;

public interface Postman {
	void send(DataPackage dataPackage);
}
