package com.knife.ant.business.blog.facade;

import java.util.List;

public interface BlogFacade {
    boolean deleteBlog(List<Long> blogIds);

    Integer blogCount(Long userId);
}
