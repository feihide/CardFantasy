package cfvbaibai.cardfantasy.engine;

import java.util.ArrayList;
import java.util.List;

import cfvbaibai.cardfantasy.CardFantasyRuntimeException;
import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.data.FeatureTag;
import cfvbaibai.cardfantasy.data.FeatureType;
import cfvbaibai.cardfantasy.data.Race;
import cfvbaibai.cardfantasy.engine.feature.BackStabFeature;
import cfvbaibai.cardfantasy.engine.feature.BlockFeature;
import cfvbaibai.cardfantasy.engine.feature.BurningFeature;
import cfvbaibai.cardfantasy.engine.feature.CounterAttackFeature;
import cfvbaibai.cardfantasy.engine.feature.CounterMagicFeature;
import cfvbaibai.cardfantasy.engine.feature.CriticalAttackFeature;
import cfvbaibai.cardfantasy.engine.feature.DodgeFeature;
import cfvbaibai.cardfantasy.engine.feature.EscapeFeature;
import cfvbaibai.cardfantasy.engine.feature.ExplodeFeature;
import cfvbaibai.cardfantasy.engine.feature.FireMagicFeature;
import cfvbaibai.cardfantasy.engine.feature.GuardFeature;
import cfvbaibai.cardfantasy.engine.feature.HealFeature;
import cfvbaibai.cardfantasy.engine.feature.HolyLightFeature;
import cfvbaibai.cardfantasy.engine.feature.IceArmorFeature;
import cfvbaibai.cardfantasy.engine.feature.IceMagicFeature;
import cfvbaibai.cardfantasy.engine.feature.ImmobilityFeature;
import cfvbaibai.cardfantasy.engine.feature.ImmueFeature;
import cfvbaibai.cardfantasy.engine.feature.LighteningMagicFeature;
import cfvbaibai.cardfantasy.engine.feature.MagicShieldFeature;
import cfvbaibai.cardfantasy.engine.feature.OverdrawFeature;
import cfvbaibai.cardfantasy.engine.feature.PenetrationFeature;
import cfvbaibai.cardfantasy.engine.feature.PrayFeature;
import cfvbaibai.cardfantasy.engine.feature.PursuitFeature;
import cfvbaibai.cardfantasy.engine.feature.RaceBuffFeature;
import cfvbaibai.cardfantasy.engine.feature.RainfallFeature;
import cfvbaibai.cardfantasy.engine.feature.RejuvenateFeature;
import cfvbaibai.cardfantasy.engine.feature.ResurrectFeature;
import cfvbaibai.cardfantasy.engine.feature.ReturnFeature;
import cfvbaibai.cardfantasy.engine.feature.ReviveFeature;
import cfvbaibai.cardfantasy.engine.feature.SnipeFeature;
import cfvbaibai.cardfantasy.engine.feature.SpikeFeature;
import cfvbaibai.cardfantasy.engine.feature.TrapFeature;
import cfvbaibai.cardfantasy.engine.feature.WeakenAllFeature;
import cfvbaibai.cardfantasy.engine.feature.WeakenFeature;
import cfvbaibai.cardfantasy.engine.feature.WoundFeature;
import cfvbaibai.cardfantasy.engine.feature.ZealotFeature;

public class FeatureResolver {
    private StageInfo stage;

    public FeatureResolver(StageInfo stage) {
        this.stage = stage;
    }

    public StageInfo getStage() {
        return this.stage;
    }

    public List<CardInfo> getAdjacentCards(CardInfo card) {
        if (card == null) {
            throw new CardFantasyRuntimeException("card is null!");
        }
        List<CardInfo> cards = new ArrayList<CardInfo>();
        cards.add(card);
        int i = card.getPosition();
        if (i > 0) {
            CardInfo leftSide = card.getOwner().getField().getCard(i - 1);
            if (leftSide != null) {
                cards.add(leftSide);
            }
        }
        CardInfo rightSide = card.getOwner().getField().getCard(i + 1);
        if (rightSide != null) {
            cards.add(rightSide);
        }
        return cards;
    }

    public void resolvePreAttackFeature(CardInfo attacker, Player defender) throws HeroDieSignal {
        for (FeatureInfo feature : attacker.getNormalUsableFeatures()) {
            if (attacker.isDead()) {
                continue;
            }
            if (feature.getType() == FeatureType.͸֧) {
                OverdrawFeature.apply(this, feature, attacker);
            }
        }
        for (FeatureInfo feature : attacker.getNormalUsableFeatures()) {
            if (attacker.isDead()) {
                continue;
            }
            if (feature.getType() == FeatureType.δ֪) {
                // JUST A PLACEHOLDER
            } else if (feature.getType() == FeatureType.����) {
                FireMagicFeature.apply(feature, this, attacker, defender, 1);
            } else if (feature.getType() == FeatureType.��ǽ) {
                FireMagicFeature.apply(feature, this, attacker, defender, 3);
            } else if (feature.getType() == FeatureType.����籩) {
                FireMagicFeature.apply(feature, this, attacker, defender, -1);
            } else if (feature.getType() == FeatureType.����) {
                LighteningMagicFeature.apply(feature, this, attacker, defender, 1, 50);
            } else if (feature.getType() == FeatureType.��������) {
                LighteningMagicFeature.apply(feature, this, attacker, defender, 3, 40);
            } else if (feature.getType() == FeatureType.�ױ�) {
                LighteningMagicFeature.apply(feature, this, attacker, defender, -1, 35);
            } else if (feature.getType() == FeatureType.����) {
                IceMagicFeature.apply(feature, this, attacker, defender, 1, 45);
            } else if (feature.getType() == FeatureType.˪������) {
                IceMagicFeature.apply(feature, this, attacker, defender, 3, 35);
            } else if (feature.getType() == FeatureType.����ѩ) {
                IceMagicFeature.apply(feature, this, attacker, defender, -1, 30);
            } else if (feature.getType() == FeatureType.����) {
                TrapFeature.apply(feature, this, attacker, defender);
            } else if (feature.getType() == FeatureType.�ѻ�) {
                SnipeFeature.apply(feature, this, attacker, defender);
            } else if (feature.getType() == FeatureType.����) {
                HealFeature.apply(feature, this, attacker);
            } else if (feature.getType() == FeatureType.����) {
                RainfallFeature.apply(feature, this, attacker);
            } else if (feature.getType() == FeatureType.����) {
                PrayFeature.apply(feature, this, attacker);
            } else if (feature.getType() == FeatureType.����) {
                ReviveFeature.apply(this, feature, attacker);
            } else if (feature.getType() == FeatureType.����) {
                BackStabFeature.apply(this, feature, attacker);
            } else if (feature.getType() == FeatureType.Ⱥ������) {
                WeakenAllFeature.apply(this, feature, attacker, defender);
            }
        }
    }

    public void resolvePostAttackFeature(CardInfo attacker, Player defender) {

    }

    public void resolveCounterAttackFeature(CardInfo attacker, CardInfo defender, Feature attackFeature) {
        if (attackFeature == null) {
            for (FeatureInfo feature : defender.getNormalUsableFeatures()) {
                if (feature.getType() == FeatureType.����) {
                    CounterAttackFeature.apply(feature, this, attacker, defender);
                } else if (feature.getType() == FeatureType.�ܴ�) {
                    SpikeFeature.apply(feature, this, attacker, defender);
                } else if (feature.getType() == FeatureType.ȼ��) {
                    BurningFeature.apply(feature, this, attacker, defender);
                }
            }
        }
    }

    public OnAttackBlockingResult resolveHealBlockingFeature(CardInfo healer, CardInfo healee, Feature feature) {
        OnAttackBlockingResult result = new OnAttackBlockingResult(true, feature.getImpact());
        if (healee.getStatus().containsStatus(CardStatusType.����)) {
            stage.getUI().healBlocked(healer, healee, feature, null);
            result.setAttackable(false);
        }
        return result;
    }

    public OnAttackBlockingResult resolveAttackBlockingFeature(CardInfo attacker, CardInfo defender, Feature feature) {
        OnAttackBlockingResult result = new OnAttackBlockingResult(true, feature == null ? attacker.getAT()
                : feature.getImpact());
        if (feature == null) {
            // Normal attack could be blocked by Dodge or ���, ����,
            // ���� status.
            CardStatus status = attacker.getStatus();
            if (status.containsStatus(CardStatusType.����) || status.containsStatus(CardStatusType.���)
                    || status.containsStatus(CardStatusType.����) || status.containsStatus(CardStatusType.����)) {
                stage.getUI().attackBlocked(attacker, defender, feature, null);
                result.setAttackable(false);
            } else {
                for (Feature blockFeature : defender.getNormalUsableFeatures()) {
                    if (blockFeature.getType() == FeatureType.����) {
                        if (!result.isAttackable()) {
                            continue;
                        }

                        result.setAttackable(!DodgeFeature.apply(blockFeature, this, attacker, defender,
                                result.getDamage()));
                    }
                }
                for (Feature blockFeature : defender.getNormalUsableFeatures()) {
                    if (!result.isAttackable()) {
                        continue;
                    }
                    if (blockFeature.getType() == FeatureType.��) {
                        result.setDamage(BlockFeature.apply(blockFeature, this, attacker, defender, result.getDamage()));
                    }
                }
                for (Feature blockFeature : defender.getNormalUsableFeatures()) {
                    if (!result.isAttackable()) {
                        continue;
                    }
                    if (blockFeature.getType() == FeatureType.����) {
                        result.setDamage(IceArmorFeature.apply(blockFeature, this, attacker, defender, result.getDamage()));
                    }
                }
            }
        } else {
            CardStatus status = attacker.getStatus();
            if (status.containsStatus(CardStatusType.����) || status.containsStatus(CardStatusType.����)
                    || status.containsStatus(CardStatusType.����)) {
                stage.getUI().attackBlocked(attacker, defender, feature, null);
                result.setAttackable(false);
            } else {
                for (Feature blockFeature : defender.getNormalUsableFeatures()) {
                    if (blockFeature.getType() == FeatureType.��������) {
                        if (CounterMagicFeature.isFeatureBlocked(this, blockFeature, feature, attacker, defender)) {
                            result.setAttackable(false);
                        }
                    } else if (blockFeature.getType() == FeatureType.����){
                        if (ImmueFeature.isFeatureBlocked(this, blockFeature, feature, attacker, defender)) {
                            result.setAttackable(false);
                        }
                    } else if (blockFeature.getType() == FeatureType.����) {
                        if (EscapeFeature.isFeatureEscaped(this, blockFeature, feature, attacker, defender)) {
                            result.setAttackable(false);
                        }
                    } else if (blockFeature.getType() == FeatureType.����) {
                        if (ImmobilityFeature.isFeatureBlocked(this, blockFeature, feature, attacker, defender)) {
                            result.setAttackable(false);
                        }
                    }
                }
                for (Feature blockFeature : defender.getNormalUsableFeatures()) {
                    if (!result.isAttackable()) {
                        continue;
                    }
                    if (blockFeature.getType() == FeatureType.ħ�� && feature.getType().containsTag(FeatureTag.ħ��)) {
                        result.setDamage(MagicShieldFeature.apply(this, blockFeature, attacker, defender,
                                result.getDamage()));
                    }
                }
            }
        }
        return result;
    }

    public void resolveDeathFeature(CardInfo attacker, CardInfo defender, Feature feature) {
        resolveLeaveFeature(defender, feature);
        for (FeatureInfo deadCardFeature : defender.getAllUsableFeatures()) {
            if (deadCardFeature.getType() == FeatureType.�Ա�) {
                ExplodeFeature.apply(this, deadCardFeature, attacker, defender);
            }
        }
        for (FeatureInfo deadCardFeature : defender.getUsableDeathFeatures()) {
            if (deadCardFeature.getType() == FeatureType.ת��) {
                ResurrectFeature.apply(this, deadCardFeature, defender);
            }
        }
    }

    public void resolveLeaveFeature(CardInfo card, Feature feature) {
        for (FeatureInfo deadCardFeature : card.getNormalUsableFeatures()) {
            if (deadCardFeature.getType() == FeatureType.����֮��) {
                RaceBuffFeature.remove(this, deadCardFeature, card, Race.����);
            } else if (deadCardFeature.getType() == FeatureType.�����ػ�) {
                RaceBuffFeature.remove(this, deadCardFeature, card, Race.����);
            }
        }
    }

    public void resolveExtraAttackFeature(CardInfo attacker, CardInfo defender, Player defenderHero,
            int normalAttackDamage) throws HeroDieSignal {
        if (attacker != null) {
            for (FeatureInfo feature : attacker.getNormalUsableFeatures()) {
                if (feature.getType() == FeatureType.����) {
                    PenetrationFeature.apply(feature, this, attacker, defenderHero, normalAttackDamage);
                } else if (feature.getType() == FeatureType.����) {
                    WeakenFeature.apply(this, feature, attacker, defender, normalAttackDamage);
                } else if (feature.getType() == FeatureType.����) {
                    WoundFeature.apply(this, feature, attacker, defender, normalAttackDamage);
                }
            }
        }
        if (defender != null) {
            for (FeatureInfo feature : defender.getNormalUsableFeatures()) {
                if (feature.getType() == FeatureType.����) {
                    ZealotFeature.apply(feature, this, attacker, defender, normalAttackDamage);
                }
            }
        }
    }

    public void resolvePreAttackCardFeature(CardInfo attacker, CardInfo defender) {
        for (FeatureInfo feature : attacker.getNormalUsableFeatures()) {
            if (feature.getType() == FeatureType.ʥ��) {
                HolyLightFeature.apply(this, feature, attacker, defender);
            } else if (feature.getType() == FeatureType.����) {
                CriticalAttackFeature.apply(this, feature, attacker, defender);
            } else if (feature.getType() == FeatureType.��׷�ʹ�) {
                PursuitFeature.apply(this, feature, attacker, defender);
            } else if (feature.getType() == FeatureType.�ͻ�) {
                ReturnFeature.apply(this, feature, attacker, defender);
            }
        }
    }

    public OnDamagedResult applyDamage(CardInfo card, int damage) {
        int originalHP = card.getHP();
        card.applyDamage(damage);
        OnDamagedResult result = new OnDamagedResult();
        if (card.getHP() <= 0) {
            result.cardDead = true;
            result.actualDamage = originalHP;
            cardDead(card);
        } else {
            result.actualDamage = damage;
        }
        return result;
    }

    public void cardDead(CardInfo deadCard) {
        this.stage.getUI().cardDead(deadCard);
        Player owner = deadCard.getOwner();
        Field field = owner.getField();
        // Set field position to null
        for (int i = 0; i < field.size(); ++i) {
            CardInfo card = field.getCard(i);
            if (deadCard == card) {
                field.expelCard(i);
                // Grave is a stack.
                owner.getGrave().insertCard(card, 0);
                break;
            }
        }
    }

    public void attackHero(CardInfo attacker, Player defenderPlayer, Feature feature, int damage) throws HeroDieSignal {
        if (attacker == null) {
            return;
        }
        stage.getUI().useSkillToHero(attacker, defenderPlayer, feature);
        if (!this.resolveAttackHeroBlockingFeatures(attacker, defenderPlayer, feature, damage)) {
            stage.getUI().attackHero(attacker, defenderPlayer, feature, damage);
            defenderPlayer.setLife(defenderPlayer.getLife() - damage);
        }
    }

    private boolean resolveAttackHeroBlockingFeatures(CardInfo attacker, Player defenderPlayer, Feature feature,
            int damage) {
        for (CardInfo defender : defenderPlayer.getField().getAliveCards()) {
            if (defender == null) {
                continue;
            }
            for (Feature defenderFeature : defender.getNormalUsableFeatures()) {
                if (defenderFeature.getType() == FeatureType.�ػ�) {
                    GuardFeature.apply(defenderFeature, this, attacker, defender, damage);
                    return true;
                }
            }
        }
        return false;
    }

    public void removeTempEffects(CardInfo card) {
        if (card == null) {
            return;
        }
        for (FeatureEffect effect : card.getEffects()) {
            FeatureType type = effect.getCause().getType();
            if (type == FeatureType.ʥ��) {
                HolyLightFeature.remove(this, effect.getCause(), card);
            } else if (type == FeatureType.����) {
                CriticalAttackFeature.remove(this, effect.getCause(), card);
            } else if (type == FeatureType.��׷�ʹ�) {
                PursuitFeature.remove(this, effect.getCause(), card);
            } else if (type == FeatureType.����) {
                BackStabFeature.remove(this, effect.getCause(), card);
            }
        }
    }

    public void resolveCardRoundEndingFeature(CardInfo card) {
        if (card == null) {
            return;
        }
        for (Feature feature : card.getNormalUsableFeatures()) {
            if (feature.getType() == FeatureType.�ش�) {
                RejuvenateFeature.apply(feature, this, card);
            }
        }
    }

    public int attackCard(CardInfo attacker, CardInfo defender) {
        this.stage.getUI().useSkill(attacker, defender, null);
        OnAttackBlockingResult blockingResult = stage.getResolver().resolveAttackBlockingFeature(attacker, defender,
                null);
        if (!blockingResult.isAttackable()) {
            return -1;
        }
        this.stage.getUI().attackCard(attacker, defender, null, blockingResult.getDamage());
        OnDamagedResult damagedResult = stage.getResolver().applyDamage(defender, blockingResult.getDamage());
        if (damagedResult.cardDead) {
            stage.getResolver().resolveDeathFeature(attacker, defender, null);
        }
        return damagedResult.actualDamage;
    }

    public CardInfo pickHealee(CardInfo healer) {
        Field field = healer.getOwner().getField();
        CardInfo healee = null;
        for (CardInfo card : field.getAliveCards()) {
            if (healee == null || card.getHP() < healee.getHP()) {
                healee = card;
            }
        }
        return healee;
    }

    public void resolveSummoningFeature(CardInfo card, Field myField, Field opField) {
        for (FeatureInfo feature : card.getUsableSummonFeatures()) {
            if (feature.getType() == FeatureType.����) {
                TrapFeature.apply(feature, this, card, opField.getOwner());
            } else if (feature.getType() == FeatureType.����籩) {
                FireMagicFeature.apply(feature, this, card, opField.getOwner(), -1);
            } else if (feature.getType() == FeatureType.�ױ�) {
                LighteningMagicFeature.apply(feature, this, card, opField.getOwner(), -1, 35);
            } else if (feature.getType() == FeatureType.����ѩ) {
                IceMagicFeature.apply(feature, this, card, opField.getOwner(), -1, 30);
            } else if (feature.getType() == FeatureType.�ͻ�) {
                ReturnFeature.apply(this, feature, card, opField.getCard(card.getPosition()));
            } else if (feature.getType() == FeatureType.Ⱥ������) {
                WeakenAllFeature.apply(this, feature, card, opField.getOwner());
            }
        }
        for (CardInfo fieldCard : myField.getAliveCards()) {
            for (FeatureInfo feature : fieldCard.getNormalUsableFeatures()) {
                if (feature.getType() == FeatureType.����֮��) {
                    RaceBuffFeature.apply(this, feature, fieldCard, Race.����, FeatureEffectType.ATTACK_CHANGE);
                } else if (feature.getType() == FeatureType.�����ػ�) {
                    RaceBuffFeature.apply(this, feature, fieldCard, Race.����, FeatureEffectType.MAXHP_CHANGE);
                }
            }
        }
    }

    public void resolveDebuff(CardInfo card, CardStatusType debuffType) {
        if (card == null) {
            return;
        }
        List<CardStatusItem> items = card.getStatus().getStatusOf(debuffType);
        for (CardStatusItem item : items) {
            this.stage.getUI().debuffDamage(card, item, item.getEffect());
            this.applyDamage(card, item.getEffect());
        }
    }

    public static class BlockStatusResult {
        private boolean blocked;

        public boolean isBlocked() {
            return blocked;
        }

        public BlockStatusResult(boolean blocked) {
            this.blocked = blocked;
        }
    }

    public BlockStatusResult resolveBlockStatusFeature(CardInfo attacker, CardInfo victim, FeatureInfo feature,
            CardStatusItem item) {
        boolean blocked = false;
        for (FeatureInfo blockFeature : victim.getNormalUsableFeatures()) {
            if (blockFeature.getType() == FeatureType.����) {
                blocked = EscapeFeature.isStatusEscaped(blockFeature, this, item, victim);
            }
        }
        return new BlockStatusResult(blocked);
    }

    public void summonCard(Player player, CardInfo card) {
        this.stage.getUI().summonCard(player, card);
        card.reset();
        card.setFirstRound(true);
        player.getField().addCard(card);
        if (this.stage.getPlayerCount() != 2) {
            throw new CardFantasyRuntimeException("There are " + this.stage.getPlayerCount()
                    + " player(s) in the stage, but expect 2");
        }
        for (Player other : stage.getPlayers()) {
            if (other != player) {
                stage.getResolver().resolveSummoningFeature(card, player.getField(), other.getField());
            }
        }
    }
}
