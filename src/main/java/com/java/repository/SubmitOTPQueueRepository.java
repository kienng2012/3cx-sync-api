package com.java.repository;

import com.java.entity.SubmitOTPQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SubmitOTPQueueRepository extends JpaRepository<SubmitOTPQueue, Long> {
//    Optional<SubmitOTPQueue> findFirstByDestAddrAndStatusOrderBySendTimestampDesc(String destAddr, Integer status);

    Optional<SubmitOTPQueue> findFirstByUsernameAndDestAddrAndStatusOrderBySendTimestampDesc(String username, String destAddr, Integer status);

    /*
    B1: Chuyen du lieu tu bang SUBMIT_OTP_QUEUE to SUBMIT_OTP_HISTORY ( dong thoi xoa ban ghi tai bang SUBMIT_OTP_QUEUE)
    B2: TODO Dong bo toi module DLR cua VNET sms smpp
     */
    @Modifying
    @Transactional
    @Query(value = "EXEC [dbo].[moveSubmitOtpToHistoryByID] :ID, :ReceiveID", nativeQuery = true)
    void moveSubmitOtpToHistoryByID(@Param("ID") long ID, @Param("ReceiveID") Long ReceiveID);

    Optional<SubmitOTPQueue> findFirstByDestAddrAndStatusOrderBySendTimestampDesc(String destAddr, Integer status);
}
