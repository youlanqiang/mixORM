package top.youlanqiang.mixorm.entity;


import java.io.Serializable;
import top.youlanqiang.mixorm.annotation.DbColumn;
import top.youlanqiang.mixorm.annotation.DbId;
import top.youlanqiang.mixorm.annotation.DbTable;
import top.youlanqiang.mixorm.annotation.IdType;

import java.sql.Timestamp;
import java.util.Date;

/**null Entity
 * @Description
 * @Author  LEI
 * @Date 2020-03-17
 */

@DbTable( "quotation_rule" )
public class Item  implements Serializable {

    private static final long serialVersionUID =  7314058416400857248L;

    @DbId( value= "id", type = IdType.INCREMENT)
    private Long id;

    /**
     * 名称
     */
    @DbColumn(  "name" )
    private String name;

    /**
     * 图片路径
     */
    @DbColumn(  "img_path" )
    private String img_path;

    /**
     * 型号
     */
    @DbColumn(  "model" )
    private String model;

    /**
     * 规格
     */
    @DbColumn(  "spec" )
    private String spec;

    /**
     * 单位
     */
    @DbColumn(  "unit" )
    private String unit;

    /**
     * 材料
     */
    @DbColumn(  "material" )
    private String material;

    /**
     * 厚度
     */
    @DbColumn(  "Thickness" )
    private String Thickness;

    /**
     * 类型
     */
    @DbColumn(  "type" )
    private String type;

    /**
     * 单价
     */
    @DbColumn(  "unit_price" )
    private Double unit_price;

    /**
     * 量度
     */
    @DbColumn(  "measure" )
    private String measure;

    /**
     * 备注
     */
    @DbColumn(  "remark" )
    private String remark;

    @DbColumn(  "status" )
    private String status;

    @DbColumn(  "create_by" )
    private String create_by;

    @DbColumn(  "create_time" )
    private Timestamp create_time;

    @DbColumn(  "modify_by" )
    private String modify_by;

    @DbColumn(  "modify_time" )
    private Timestamp modify_time;

    @DbColumn(  "mtype" )
    private String mtype;

    /**
     * 条件2
     */
    @DbColumn(  "map2" )
    private String map2;

    /**
     * 条件3
     */
    @DbColumn(  "map3" )
    private String map3;

    /**
     * 条件4
     */
    @DbColumn(  "map4" )
    private String map4;

    /**
     * 条件5
     */
    @DbColumn(  "map5" )
    private String map5;

    /**
     * 工厂价
     */
    @DbColumn(  "factory_price" )
    private Double factory_price;

    /**
     //	 * 过期时间
     //	 */
    @DbColumn(  "due" )
    private Timestamp due;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getThickness() {
        return Thickness;
    }

    public void setThickness(String thickness) {
        Thickness = thickness;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getModify_by() {
        return modify_by;
    }

    public void setModify_by(String modify_by) {
        this.modify_by = modify_by;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getMap2() {
        return map2;
    }

    public void setMap2(String map2) {
        this.map2 = map2;
    }

    public String getMap3() {
        return map3;
    }

    public void setMap3(String map3) {
        this.map3 = map3;
    }

    public String getMap4() {
        return map4;
    }

    public void setMap4(String map4) {
        this.map4 = map4;
    }

    public String getMap5() {
        return map5;
    }

    public void setMap5(String map5) {
        this.map5 = map5;
    }

    public Double getFactory_price() {
        return factory_price;
    }

    public void setFactory_price(Double factory_price) {
        this.factory_price = factory_price;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Timestamp due) {
        this.due = due;
    }
}

