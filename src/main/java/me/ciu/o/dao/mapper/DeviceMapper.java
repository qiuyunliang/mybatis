package me.ciu.o.dao.mapper;

import me.ciu.o.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface DeviceMapper {
    Device selectDevice(@Param("id") long id);
    void insertDevice(Device device);
    void updateDevice(Device device);
    void deleteDevice(@Param("id") long id);
}