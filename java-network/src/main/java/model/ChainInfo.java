package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by jc on 5/24/18.
 */
public class ChainInfo {


    @SerializedName("server_version")
    private String serverVersion;

    @SerializedName("head_block_num")
    private long headBlockNum;

    @SerializedName("last_irreversible_block_num")
    private long lastIrreversibleBlockNum;

    @SerializedName("last_irreversible_block_id")
    private String lastIrreversibleBlockId;

    @SerializedName("head_block_id")
    private String headBlockId;

    @SerializedName("head_block_time")
    private Date headBlockTime;

    @SerializedName("head_block_producer")
    private String headBlockProducer;

    @SerializedName("virtual_block_cpu_limit")
    private long virtualBlockCpuLimit;

    @SerializedName("virtual_block_net_limit")
    private long virtualBlockNetLimit;

    @SerializedName("block_cpu_limit")
    private long blockCpuLimit;

    @SerializedName("block_net_limit")
    private long blockNetLimit;

    public String getServerVersion() {
        return serverVersion;
    }

    public long getHeadBlockNum() {
        return headBlockNum;
    }

    public long getLastIrreversibleBlockNum() {
        return lastIrreversibleBlockNum;
    }

    public String getLastIrreversibleBlockId() {
        return lastIrreversibleBlockId;
    }

    public String getHeadBlockId() {
        return headBlockId;
    }

    public Date getHeadBlockTime() {
        return headBlockTime;
    }

    public String getHeadBlockProducer() {
        return headBlockProducer;
    }

    public long getVirtualBlockCpuLimit() {
        return virtualBlockCpuLimit;
    }

    public long getVirtualBlockNetLimit() {
        return virtualBlockNetLimit;
    }

    public long getBlockCpuLimit() {
        return blockCpuLimit;
    }

    public long getBlockNetLimit() {
        return blockNetLimit;
    }
}
