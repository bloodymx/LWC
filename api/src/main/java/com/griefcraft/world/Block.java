/*
 * Copyright (c) 2011, 2012, Tyler Blair
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package com.griefcraft.world;

public abstract class Block {

    /**
     * Gets the block's type
     * @return
     */
    public abstract int getType();

    /**
     * Gets the block's data
     * @return
     */
    public abstract byte getData();

    /**
     * Get the world this block is located in
     * @return
     */
    public abstract World getWorld();

    /**
     * Gets the block's x coordinate
     * 
     * @return
     */
    public abstract int getX();

    /**
     * Gets the block's y coordinate
     * 
     * @return
     */
    public abstract int getY();

    /**
     * Gets the block's z coordinate
     *
     * @return
     */
    public abstract int getZ();

    /**
     * Set the block's type
     *
     * @param type
     */
    public abstract void setType(int type);

    /**
     * Set the block's data
     *
     * @param data
     */
    public abstract void setData(byte data);

    /**
     * Get the block's current location
     * @return
     */
    public Location getLocation() {
        return new Location(getWorld(), getX(), getY(), getZ());
    }

    /**
     * Get a block relative to this block
     *
     * @param deltaX
     * @param deltaY
     * @param deltaZ
     * @return
     */
    public Block getRelative(int deltaX, int deltaY, int deltaZ) {
        return getWorld().getBlockAt(getX() + deltaX, getY() + deltaY, getZ() + deltaZ);
    }

    /**
     * Finds a block relative to the block with the given block type. This does not look UP or DOWN and only looks
     * on the current plane for the block (that is, the current y-level)
     *
     * @param type
     * @return the Block found. If it was not found, NULL will be returned
     */
    public Block findBlockRelative(int type) {
        Block block;

        // First, the x plane
        if ((block = getRelative(-1, 0, 0)) != null) {
            if (block.getType() == type) {
                return block;
            }
        }

        else if ((block = getRelative(1, 0, 0)) != null) {
            if (block.getType() == type) {
                return block;
            }
        }

        // now the z plane
        else if ((block = getRelative(0, 0, -1)) != null) {
            if (block.getType() == type) {
                return block;
            }
        }

        else if ((block = getRelative(0, 0, 1)) != null) {
            if (block.getType() == type) {
                return block;
            }
        }

        // nothing at all found QQ
        return block;
    }

}
