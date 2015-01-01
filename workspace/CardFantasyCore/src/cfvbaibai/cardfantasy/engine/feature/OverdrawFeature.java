package cfvbaibai.cardfantasy.engine.feature;

import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.data.Skill;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.SkillEffect;
import cfvbaibai.cardfantasy.engine.SkillEffectType;
import cfvbaibai.cardfantasy.engine.FeatureInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;
import cfvbaibai.cardfantasy.engine.HeroDieSignal;

public final class OverdrawFeature {
    public static void apply(FeatureResolver resolver, FeatureInfo featureInfo, CardInfo attacker) throws HeroDieSignal {
        Skill skill = featureInfo.getFeature();
        int adjAT = skill.getImpact();
        GameUI ui = resolver.getStage().getUI();
        ui.useSkill(attacker, skill, true);
        ui.adjustAT(attacker, attacker, adjAT, skill);
        attacker.addEffect(new SkillEffect(SkillEffectType.ATTACK_CHANGE, featureInfo, adjAT, true));
        ui.attackCard(attacker, attacker, skill, adjAT);
        if (resolver.applyDamage(attacker, adjAT).cardDead) {
            resolver.resolveDeathFeature(attacker, attacker, skill);
        }
    }
}
