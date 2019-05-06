package com.lqstar.mapper;

import com.lqstar.model.Menu;
import com.lqstar.model.PageMenu;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/25/025 22:32
 */
@Component
public interface MenuMapper {
    List<PageMenu> selectMenuList();
    List<Menu> selectMenus();
}
