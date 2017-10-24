/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWar;

import java.awt.Rectangle;

/**
 *
 * @author Administrator
 */
public interface Block {

    public abstract Rectangle getRect();

    public abstract boolean hitedBy(Bullet b);
}
