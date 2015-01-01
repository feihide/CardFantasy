package cfvbaibai.cardfantasy.engine.feature;

import java.util.List;

import cfvbaibai.cardfantasy.CardFantasyRuntimeException;
import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.data.Skill;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.SkillEffect;
import cfvbaibai.cardfantasy.engine.SkillEffectType;
import cfvbaibai.cardfantasy.engine.FeatureInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;

public final class WarthFeature {
    public static void apply(FeatureResolver resolver, FeatureInfo featureInfo, CardInfo attacker, CardInfo defender) {
        if (attacker == null || attacker.isDead()) {
            throw new CardFantasyRuntimeException("attacker is null or dead!");
        }
        Skill skill = featureInfo.getFeature();
        int adjAT = skill.getImpact() * attacker.getLevel1AT() / 100;
        GameUI ui = resolver.getStage().getUI();
        if (attacker.getHP() < defender.getHP()) {
            ui.useSkill(attacker, defender, skill, true);
            ui.adjustAT(attacker, attacker, adjAT, skill);
            attacker.addEffect(new SkillEffect(SkillEffectType.ATTACK_CHANGE, featureInfo, adjAT, false));
        }
    }

    public static void remove(FeatureResolver resolver, FeatureInfo feature, CardInfo card) {
        List<SkillEffect> effects = card.getEffectsCausedBy(feature);
        for (SkillEffect effect : effects) {
            resolver.getStage().getUI().loseAdjustATEffect(card, effect);
            card.removeEffect(effect);
        }
    }
}
