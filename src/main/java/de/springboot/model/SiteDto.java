package de.springboot.model;

import lombok.Data;

import java.util.List;

/**
 * User: victoria.shepard
 * Date: 18/11/2018
 * Time: 15:05
 */
@Data
public class SiteDto {

   private List<Item> items;
}
