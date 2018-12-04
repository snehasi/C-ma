package com.donut.cma;

public class CropPrices {

    public String timestamp,thestate,district,market,commodity,variety,arrival_date,min_price,max_price,modal_price;

    public CropPrices(String timestamp,String thestate,String district,String market,String commodity,String variety,String arrival_date,String min_price,String max_price,String modal_price){
        this.timestamp=timestamp.replaceAll("\"","");
        this.thestate=thestate.replaceAll("\"","");
        this.district=district.replaceAll("\"","");
        this.market=market.replaceAll("\"","");
        this.commodity=commodity.replaceAll("\"","");
        this.variety=variety.replaceAll("\"","");
        this.arrival_date=arrival_date.replaceAll("\"","");
        this.min_price=min_price.replaceAll("\"","");
        this.max_price=max_price.replaceAll("\"","");
        this.modal_price=modal_price.replaceAll("\"","");
    }
}