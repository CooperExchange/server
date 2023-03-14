
package com.cooperex.cex.controller;
import com.cooperex.cex.dao.AssetPriceDAO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class AssetPriceController {
    private AssetPriceDAO assetPriceDAO;

    public AssetPriceController() {
        System.out.println("AssetPriceDAO Controller object has been initialized!");
        assetPriceDAO = new AssetPriceDAO();
    }
    
    @CrossOrigin
    @GetMapping("/asset-price")
    public ResponseEntity<String> getAssetPrice() {
        String assetPriceJson = assetPriceDAO.getAssetPrice();
        if (assetPriceJson != "{}") {
            return ResponseEntity.ok(assetPriceJson);
        }
        return ResponseEntity.badRequest()
                .body("Fetching price failed. Please check your price APIs");
    }
}



