package cn.imust.service;

import cn.imust.domain.BedRoom;
import cn.imust.domain.PageBeanUI;
import cn.imust.domain.Room;

import java.util.List;

public interface BedRoomService {
    void addBedRoom(BedRoom bedRoom,Room room);

    /**
     * 查询床位信息列表
     * @param pageBeanUI
     * @return
     */
    List<BedRoom> findAllBedroom(PageBeanUI pageBeanUI);
}
