
package com.salesmanager.shop.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.core.business.utils.DateUtil;

@Component
public class EmailUtils {
    
    private final static String EMAIL_STORE_NAME = "EMAIL_STORE_NAME";
    private final static String EMAIL_FOOTER_COPYRIGHT = "EMAIL_FOOTER_COPYRIGHT";
    private final static String EMAIL_DISCLAIMER = "EMAIL_DISCLAIMER";
    private final static String EMAIL_SPAM_DISCLAIMER = "EMAIL_SPAM_DISCLAIMER";
    private final static String EMAIL_ADMIN_LABEL = "EMAIL_ADMIN_LABEL";
    private final static String LOGOPATH = "LOGOPATH";
    
    @Inject
    @Qualifier("img")
    private ImageFilePath imageUtils;
    
    /**
     * Builds generic html email information
     * @param contextPath Path to the web application
     * @param store The merchant store
     * @param messages Label utilities for i18n
     * @param locales List of locales to generate email content for
     * @return Map of email template tokens with localized values
     */
    public Map<String, Map<Locale, String>> createEmailObjectsMap(String contextPath, MerchantStore store, LabelUtils messages, List<Locale> locales) {
        Map<String, Map<Locale, String>> templateTokens = new HashMap<>();
        
        String[] adminNameArg = {store.getStorename()};
        String[] adminEmailArg = {store.getStoreEmailAddress()};
        String[] copyArg = {store.getStorename(), DateUtil.getPresentYear()};
        
        for (Locale locale : locales) {
            // Admin label
            Map<Locale, String> adminLabel = templateTokens.computeIfAbsent(EMAIL_ADMIN_LABEL, k -> new HashMap<>());
            adminLabel.put(locale, messages.getMessage("email.message.from", adminNameArg, locale));
            
            // Store name
            Map<Locale, String> storeName = templateTokens.computeIfAbsent(EMAIL_STORE_NAME, k -> new HashMap<>());
            storeName.put(locale, store.getStorename());
            
            // Copyright
            Map<Locale, String> copyright = templateTokens.computeIfAbsent(EMAIL_FOOTER_COPYRIGHT, k -> new HashMap<>());
            copyright.put(locale, messages.getMessage("email.copyright", copyArg, locale));
            
            // Disclaimer
            Map<Locale, String> disclaimer = templateTokens.computeIfAbsent(EMAIL_DISCLAIMER, k -> new HashMap<>());
            disclaimer.put(locale, messages.getMessage("email.disclaimer", adminEmailArg, locale));
            
            // Spam disclaimer
            Map<Locale, String> spam = templateTokens.computeIfAbsent(EMAIL_SPAM_DISCLAIMER, k -> new HashMap<>());
            spam.put(locale, messages.getMessage("email.spam.disclaimer", locale));
        }
        
        // Logo handling
        if(store.getStoreLogo() != null) {
            StringBuilder logoPath = new StringBuilder();
            String scheme = Constants.HTTP_SCHEME;
            logoPath.append("<img src='")
                   .append(scheme)
                   .append("://")
                   .append(store.getDomainName())
                   .append(contextPath)
                   .append("/")
                   .append(imageUtils.buildStoreLogoFilePath(store))
                   .append("' style='max-width:400px;'>");
            
            Map<Locale, String> logo = templateTokens.computeIfAbsent(LOGOPATH, k -> new HashMap<>());
            for (Locale locale : locales) {
                logo.put(locale, logoPath.toString());
            }
        } else {
            Map<Locale, String> logo = templateTokens.computeIfAbsent(LOGOPATH, k -> new HashMap<>());
            for (Locale locale : locales) {
                logo.put(locale, store.getStorename());
            }
        }
        
        return templateTokens;
    }
}