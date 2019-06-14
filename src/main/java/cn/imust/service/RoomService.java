package cn.imust.service;


import cn.imust.domain.PageBeanUI;
import cn.imust.domain.Room;
import cn.imust.domain.User;

import java.util.List;

public interface RoomService {

    List<Room> findAll(User user);

    void addRoom(Room room);
}
