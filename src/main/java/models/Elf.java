package models;

import lombok.Data;

@Data
public class Elf {

    private Integer carryWeight;

    public Elf() {
        this.carryWeight = 0;
    }

    public void addCarryWeight(Integer additionalWeight) {
        this.carryWeight += additionalWeight;
    }

}