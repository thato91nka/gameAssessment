package org.assessment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PitDTO {
    private int id;
    private PlayerDTO playerDTO;
    private int numStones;

    public Boolean isEmpty() {
        return this.numStones == 0;
    }
    public void clear(){
        this.numStones =0;
    }
    public void addStones(int stones){
        this.numStones+=stones;
    }

    public void sow() {
        this.numStones++;
    }

    @Override
    public String toString() {
        return "PitDTO{" +
                "id=" + id +
                ", playerDTO=" + playerDTO +
                ", numStones=" + numStones +
                '}';
    }
}
