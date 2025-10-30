package com.lvwyh.service;

import com.lvwyh.ao.NodeNamingCheckAO;
import com.lvwyh.ao.ProjectSpaceNamingCheckAO;
import com.lvwyh.ao.TableNamingCheckAO;

import java.util.Map;

public interface NamingStandardService {
    Map<String, Object> checkTableNaming(TableNamingCheckAO request);

    Map<String, Object> checkNodeNaming(NodeNamingCheckAO request);

    Map<String, Object> checkProjectSpaceNaming(ProjectSpaceNamingCheckAO request);
}
