package com.heo.common.utils;

/**
 * @author: justinniu
 * @date: 2018/11/14
 * @desc: 雪花ID生成
 */
public class SnowFlowerUtils {
    private static SnowFlower DEFALUT_INSTANCE = new SnowFlower(1, 2);

    private static class SnowFlower {

        private final static int DEFAULT_MACHINE_BIT_NUM = 5;

        private final static int DEFAULT_IDC_BIT_NUM = 5;
        /**
         * 开始时间 2018/11/14/ 18:07:07
         */
        private final static long START_STAMP = 1542189739L;
        /**
         * 剩余字节数
         */
        private final static int REMAIN_BIT_NUM = 22;

        /**
         * 中心ID
         */
        private long idcId;

        /**
         * 机器ID
         */
        private long machineId;

        /**
         * 当前序列号
         */
        private long sequence = 0l;

        /**
         * 上次更新时间戳
         */
        private long lastStamp = -1L;

        /**
         * idc偏移量
         */
        private int idcBitLeftOffset;

        /**
         * 机器偏移量
         */
        private int machineBitLeftOffset;

        /**
         * 时间戳偏移量
         */
        private int timestampBitLeftOffset;

        /**
         * 最大序列值
         */
        private int maxSequenceValue;

        private int idcIdBitNum;

        private int machineBitNum;

        private SnowFlower(long idcId, long machineId) {
            new SnowFlower(DEFAULT_IDC_BIT_NUM, DEFAULT_MACHINE_BIT_NUM, idcId, machineId);
        }

        private SnowFlower(int idcIdBitNum, int machineBitNum, long idcId, long machineId) {

            this.idcIdBitNum = idcIdBitNum;

            this.machineBitNum = machineBitNum;

            this.idcId = idcId;

            this.machineId = machineId;

            init();
        }

        private void init() {
            int sequenceBitNum = REMAIN_BIT_NUM - idcIdBitNum - machineBitNum;
            if (idcIdBitNum <= 0 || machineBitNum <= 0 || sequenceBitNum <= 0) {
                throw new IllegalArgumentException("error bit number");
            }
            this.maxSequenceValue = ~(-1 << sequenceBitNum);
            this.machineBitLeftOffset = sequenceBitNum;
            this.idcBitLeftOffset = idcIdBitNum + sequenceBitNum;
            this.timestampBitLeftOffset = idcIdBitNum + machineBitNum + sequenceBitNum;
        }

        private void setIdcId(long idcId) {
            this.idcId = idcId;
        }

        private void setMachineId(long machineId) {
            this.machineId = machineId;
        }

        private void setIdcIdBitNum(int idcIdBitNum) {
            this.idcIdBitNum = idcIdBitNum;
        }

        private void setMachineBitNum(int machineBitNum) {
            this.machineBitNum = machineBitNum;
        }

        public synchronized long nextId() {
                long currentStamp = System.currentTimeMillis();
                if (currentStamp < lastStamp) {
                    throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastStamp - currentStamp));
                } else {
                    sequence = 0L;
                }
                lastStamp = currentStamp;
                return (currentStamp - START_STAMP) << timestampBitLeftOffset | idcId << idcBitLeftOffset | machineId << machineBitLeftOffset | sequence;
            }

    }

    public static long createId() {
        return DEFALUT_INSTANCE.nextId();
    }

    /**
     * 更改ID
     * @param idcId
     * @param mechineId
     * @return
     */
    public static synchronized long createId(long idcId, long mechineId) {
        DEFALUT_INSTANCE.setIdcId(idcId);
        DEFALUT_INSTANCE.setMachineId(mechineId);
        return DEFALUT_INSTANCE.nextId();
    }

    public static synchronized long createId(int idcIdBitNum, int machineBitNum, long idcId, long machineId) {
        DEFALUT_INSTANCE.setIdcIdBitNum(idcIdBitNum);
        DEFALUT_INSTANCE.setMachineBitNum(machineBitNum);
        DEFALUT_INSTANCE.setIdcId(idcId);
        DEFALUT_INSTANCE.setMachineId(machineId);
        DEFALUT_INSTANCE.init();
        return DEFALUT_INSTANCE.nextId();
    }

    public static void main(String[] args) {
        System.out.println(createId());
        System.out.println(createId(100, 1000));
        System.out.printf("%s %n", "zwt");
    }


}
