package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * <p>ContractListData class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class ContractListData {
    @JSONField
    public int ID = 0;
    @JSONField
    public String name = "";
    @JSONField
    public List<String> skillRequirements;

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ContractListData{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", skills=" + skillRequirements +
                '}';
    }
}
