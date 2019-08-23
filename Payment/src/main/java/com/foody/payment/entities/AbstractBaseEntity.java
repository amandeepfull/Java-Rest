package com.foody.payment.entities;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 7407155022997843530L;

    @Id
    protected String id;

    @Index
    protected long createdAt;

    @Index
    protected long modifiedAt;

    @OnSave
    public void updateTimeStamps() {

        this.modifiedAt = System.currentTimeMillis();
        if (this.createdAt <= 0)
            this.createdAt = modifiedAt;
    }

}
