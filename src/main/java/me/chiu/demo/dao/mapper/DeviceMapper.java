package me.chiu.demo.dao.mapper;

import me.chiu.demo.entity.Device;
import org.apache.ibatis.annotations.Param;

public interface DeviceMapper {
    Device selectDevice(@Param("id") long id);
    void insertDevice(Device device);
    void updateDevice(Device device);
    void deleteDevice(@Param("id") long id);
}