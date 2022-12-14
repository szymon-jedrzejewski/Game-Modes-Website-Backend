package com.gmw.services.game.impl;

import com.gmw.game.tos.ExistingGameTO;
import com.gmw.model.Game;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.TOConverter;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.game.DBGameReadService;

import java.util.List;

public class DBGameReadServiceImpl extends DBService implements DBGameReadService, TOConverter<ExistingGameTO, Game> {
    
    public DBGameReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingGameTO obtainGameById(Long id) throws ResourceNotFoundException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("games");
        querySpec.setClazz(Game.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));

        return ServiceUtils.find(repository, this, querySpec).get(0);
    }

    @Override
    public ExistingGameTO obtainGameByName(String name) throws ResourceNotFoundException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("games");
        querySpec.setClazz(Game.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, name));

        return ServiceUtils.find(repository, this, querySpec).get(0);
    }

    @Override
    public List<ExistingGameTO> obtainAllGames() throws ResourceNotFoundException {
        Repository<Game> repository = getRepositoryManager().getGameRepository();
        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Game.class);
        querySpec.setTableName("games");

        return ServiceUtils.find(repository, this, querySpec);
    }

    @Override
    public ExistingGameTO convert(Game game) {
        return ExistingGameTO
                .builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .avatar(game.getAvatar())
                .build();
    }
}
