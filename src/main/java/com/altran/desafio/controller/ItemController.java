package com.altran.desafio.controller;

import com.altran.desafio.exceptions.DesafioException;
import com.altran.desafio.exceptions.ModeloInvalidoException;
import com.altran.desafio.model.Item;
import com.altran.desafio.repos.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ItemController {

    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public void save(Item item){
        try {
            item.valida();
        } catch (NullPointerException | ModeloInvalidoException e) {
            throw new DesafioException("O item nao e valido.");
        }
        itemRepository.save(item);
    }

    public void delete(Item item){
        itemRepository.delete(item);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findById(String id){
        return itemRepository.findById(id).get();
    }

}
