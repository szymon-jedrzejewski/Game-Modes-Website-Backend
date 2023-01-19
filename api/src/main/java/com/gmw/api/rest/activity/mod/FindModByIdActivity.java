package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.activity.mod.tos.CommentDTO;
import com.gmw.api.rest.activity.mod.tos.ModDTO;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.user.DBUserReadService;
import com.gmw.user.tos.ExistingUserTO;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindModByIdActivity extends Activity<ModDTO> {

    private final Long modId;

    public FindModByIdActivity(Long modId) {
        this.modId = modId;
    }

    @Override
    protected ModDTO realExecute() throws ResourceNotFoundException {
        try(ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            return prepareModDTO(serviceManager);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }

    private ModDTO prepareModDTO(ServiceManager serviceManager) throws ResourceNotFoundException {
        DBUserReadService dbUserReadService = serviceManager.getDbUserReadService();

        ExistingModTO existingModTO = serviceManager.getDbModReadService().findModById(modId);
        List<ExistingCommentTO> existingCommentTOS = serviceManager.getDbCommentReadService().obtainCommentsByModId(modId);

        String category = serviceManager.getDbCategoryService().obtainCategoryById(existingModTO.getCategoryId()).getName();
        String gameName = serviceManager.getDbGameReadService().obtainGameById(existingModTO.getGameId()).getName();
        Double rating = serviceManager.getDbRatingReadService().obtainRatingForMod(modId);

        String userName = dbUserReadService.obtainUserById(existingModTO.getUserId()).getName();
        Map<Long, ExistingUserTO> userIdToUser = dbUserReadService.obtainUsersByIds(
                existingCommentTOS
                        .stream()
                        .map(ExistingCommentTO::getUserId)
                        .collect(Collectors.toList())).stream()
                .collect(Collectors.toMap(ExistingUserTO::getId, Function.identity()));


        return new ModDTO(existingModTO.getName(),
                userName,
                gameName,
                existingModTO.getDescription(),
                existingModTO.getDownloadLink(),
                existingModTO.getFieldsValues(),
                existingModTO.getDate(),
                category,
                existingModTO.getAvatar(),
                existingCommentTOS
                        .stream()
                        .map(comment -> mapComment(comment, userIdToUser))
                        .collect(Collectors.toList()),
                rating);
    }

    private CommentDTO mapComment(ExistingCommentTO existingCommentTO, Map<Long, ExistingUserTO> userIdToUser) {

        return new CommentDTO(userIdToUser.get(existingCommentTO.getUserId()).getName(),
                existingCommentTO.getComment(),
                existingCommentTO.getCreationDate().toString());
    }
}
