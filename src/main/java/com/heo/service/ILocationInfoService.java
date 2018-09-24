package com.heo.service;

import com.heo.entity.mapper.LocationInfo;

import javax.xml.stream.Location;
import java.util.List;

/**
 * @Auth justinniu
 * @Date 2018/9/24
 * @Desc
 */
public interface ILocationInfoService {
    List<LocationInfo> getLocationsByPart(Byte partId);
    LocationInfo getLocationById(Byte id);
    LocationInfo getLocationByName(String name);
}
