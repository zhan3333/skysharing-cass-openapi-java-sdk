package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ContractListData {
    @JSONField
    public int ID = 0;
    @JSONField
    public String name = "";
    @JSONField
    public List<String> skillRequirements;

    @Override
    public String toString() {
        return "ContractListData{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", skills=" + skillRequirements +
                '}';
    }
}
