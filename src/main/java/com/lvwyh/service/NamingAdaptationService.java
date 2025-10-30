package com.lvwyh.service;

import com.lvwyh.ao.NamingAdaptationAO;

import java.util.Map;

public interface NamingAdaptationService {
    Map<String, Object> adaptName(NamingAdaptationAO request);
}
