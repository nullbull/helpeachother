package com.heo.common.utils;

/**
 * @author: justinniu
 * @date: 2018/11/14
 * @desc: 雪花ID生成
 */
public class SnowFlowerUtils {
    private static SnowFlower DEFALUT_INSTANCE = new SnowFlower(1, 2);

//    private static class Factory {
//
//
//        private int machineBitNum;
//
//        private int idcIdBitNum;
//        {
//            this.machineBitNum = DEFAULT_MACHINE_BIT_NUM;
//            this.idcIdBitNum = DEFAULT_IDC_BIT_NUM;
//        }
//        public Factory() {}
//        public Factory(int machineNum, int centerNum) {
//            this.machineBitNum = machineNum;
//            this.idcIdBitNum = centerNum;
//        }
//        public SnowFlower create(long idcId, long machineId) {
//            return new SnowFlower(idcIdBitNum, machineBitNum, idcId, machineId);
//        }
//    }
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

        private SnowFlower(long idcId, long machineId) {
            new SnowFlower(DEFAULT_IDC_BIT_NUM, DEFAULT_IDC_BIT_NUM, idcId, machineId);
        }

        private SnowFlower(int idcIdBitNum, int machineBitNum, long idcId, long machineId) {
            int sequenceBitNum = REMAIN_BIT_NUM - idcIdBitNum - machineBitNum;

            if (idcIdBitNum <= 0 || machineBitNum <= 0 || sequenceBitNum <= 0) {
                throw new IllegalArgumentException("error bit number");
            }
            this.maxSequenceValue = ~(-1 << sequenceBitNum);

            machineBitLeftOffset = sequenceBitNum;
            idcBitLeftOffset = idcIdBitNum + sequenceBitNum;

            timestampBitLeftOffset = idcIdBitNum + machineBitNum + sequenceBitNum;

            this.idcId = idcId;

            this.machineId = machineId;
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

    public static long createId(long idcId, long mechineId) {
        SnowFlower sn = new SnowFlower(idcId, mechineId);
        return sn.nextId();
    }


}
