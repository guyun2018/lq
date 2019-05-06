package com.lqstar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/11/011 23:56
 */
@Setter
@Getter
public class Menu {

  private String id;
  private String path;
  private String pid;
  private String component;
    @JsonInclude(JsonInclude.Include.NON_NULL)
  private String redirect;
  private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private List<Menu> children;
  private Meta meta;
  private String title;
  private String icon;
  private Integer orderNumber;


}
