package org.spring.e1i4TeamProject.shop.service;

import lombok.RequiredArgsConstructor;
import org.spring.e1i4TeamProject.shop.service.serviceImpl.ShopFileServiceImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopFileService implements ShopFileServiceImpl {
}
