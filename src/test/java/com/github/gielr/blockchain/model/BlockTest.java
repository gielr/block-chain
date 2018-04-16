package com.github.gielr.blockchain.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

public class BlockTest {
    @Test
    public void testHash() {
        Block block = new Block("Hello World!", UUID.randomUUID().toString());
        System.out.println(block.getHash());
        System.out.println(block.getPreviousHash());

        Block second = new Block("Hello Jon!", block.getHash());
        System.out.println(second.getHash());
        System.out.println(second.getPreviousHash());
    }

    @Test
    public void shouldGenerateHashesWith5LeadingZeros(){
        Block first = new Block("Hello World!", UUID.randomUUID().toString());
        first.mine(5);
        Block second = new Block("Hello Jon!", first.getHash());
        second.mine(5);
        Assertions.assertThat(first.getHash()).startsWith("00000");
    }
}