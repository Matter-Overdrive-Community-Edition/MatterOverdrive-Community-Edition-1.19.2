//package matteroverdrive.common.cable_network;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//import matteroverdrive.core.cable.AbstractNetwork;
//import matteroverdrive.core.cable.CableNetworkRegistry;
//import matteroverdrive.core.cable.types.matter_network.IMatterNetworkCable;
//import net.minecraft.core.Direction;
//import net.minecraft.world.level.block.entity.BlockEntity;
//
//public class MatterNetwork extends AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack> {
//	private double resistance;
//	private double energyLoss;
//	private double voltage = 0.0;
//	private double lastEnergyLoss;
//	private double lastVoltage = 0.0;
//	private ArrayList<BlockEntity> currentProducers = new ArrayList<>();
//	private double transferBuffer = 0;
//	private double maxTransferBuffer;
//
//	public double getLastEnergyLoss() {
//		return lastEnergyLoss;
//	}
//
//	public double getActiveVoltage() {
//		return lastVoltage;
//	}
//
//	public double getResistance() {
//		return resistance;
//	}
//
//	public MatterNetwork() {
//		this(new HashSet<IMatterNetworkCable>());
//	}
//
//	public MatterNetwork(Collection<? extends IMatterNetworkCable> varCables) {
//		conductorSet.addAll(varCables);
//		CableNetworkRegistry.register(this);
//	}
//
//	public MatterNetwork(Set<AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack>> networks) {
//		for (AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack> net : networks) {
//			if (net != null) {
//				conductorSet.addAll(net.conductorSet);
//				net.deregister();
//			}
//		}
//		refresh();
//		NetworkRegistry.register(this);
//	}
//
//	public MatterNetwork(Set<MatterNetwork> networks, boolean special) {
//		for (MatterNetwork net : networks) {
//			if (net != null) {
//				conductorSet.addAll(net.conductorSet);
//				net.deregister();
//			}
//		}
//		refresh();
//		NetworkRegistry.register(this);
//	}
//
//	private TransferPack sendToReceivers(TransferPack maxTransfer, ArrayList<BlockEntity> ignored, boolean debug) {
//		if (maxTransfer.getJoules() > 0 && maxTransfer.getVoltage() > 0) {
//			Set<BlockEntity> availableAcceptors = getEnergyAcceptors();
//			double joulesSent = 0;
//			availableAcceptors.removeAll(ignored);
//			if (!availableAcceptors.isEmpty()) {
//				Iterator<BlockEntity> it = availableAcceptors.iterator();
//				double totalUsage = 0;
//				HashMap<BlockEntity, Double> usage = new HashMap<>();
//				while (it.hasNext()) {
//					BlockEntity receiver = it.next();
//					double localUsage = 0;
//					if (acceptorInputMap.containsKey(receiver)) {
//						boolean shouldRemove = true;
//						for (Direction connection : acceptorInputMap.get(receiver)) {
//							TransferPack pack = ElectricityUtils.receivePower(receiver, connection, TransferPack.joulesVoltage(maxTransfer.getJoules(), maxTransfer.getVoltage()), true);
//							if (pack.getJoules() != 0) {
//								shouldRemove = false;
//								totalUsage += pack.getJoules();
//								localUsage += pack.getJoules();
//								break;
//							}
//						}
//						if (shouldRemove) {
//							it.remove();
//						}
//					}
//					usage.put(receiver, localUsage);
//				}
//				for (BlockEntity receiver : availableAcceptors) {
//					TransferPack dedicated = TransferPack.joulesVoltage(maxTransfer.getJoules() * (usage.get(receiver) / totalUsage), maxTransfer.getVoltage());
//					if (acceptorInputMap.containsKey(receiver)) {
//						TransferPack perConnection = TransferPack.joulesVoltage(dedicated.getJoules() / acceptorInputMap.get(receiver).size(), maxTransfer.getVoltage());
//						for (Direction connection : acceptorInputMap.get(receiver)) {
//							TransferPack pack = ElectricityUtils.receivePower(receiver, connection, perConnection, debug);
//							joulesSent += pack.getJoules();
//							if (!debug) {
//								transmittedThisTick += pack.getJoules();
//							}
//						}
//					}
//				}
//			}
//			return TransferPack.joulesVoltage(Math.min(maxTransfer.getJoules(), joulesSent), maxTransfer.getVoltage());
//		}
//		return TransferPack.EMPTY;
//	}
//
//	public Set<BlockEntity> getEnergyAcceptors() {
//		return new HashSet<>(acceptorSet);
//	}
//
//	private boolean checkForOverload() {
//		if (networkMaxTransfer * voltage - transmittedThisTick <= 0 && voltage > 0) {
//			HashSet<SubtypeWire> checkList = new HashSet<>();
//			for (SubtypeWire type : SubtypeWire.values()) {
//				if (type != SubtypeWire.superconductive && type != SubtypeWire.insulatedsuperconductive && type != SubtypeWire.logisticssuperconductive && type.capacity <= transmittedLastTick / voltage * 20 && type.capacity <= transmittedThisTick / voltage * 20) {
//					checkList.add(type);
//				}
//			}
//			for (SubtypeWire index : checkList) {
//				for (IMatterNetworkCable conductor : conductorTypeMap.get(index)) {
//					Scheduler.schedule(1, conductor::destroyViolently);
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public void updateStatistics(IMatterNetworkCable cable) {
//		super.updateStatistics(cable);
//		resistance += cable.getWireType().resistance;
//	}
//
//	@Override
//	public void updateStatistics() {
//		resistance = 0;
//		super.updateStatistics();
//	}
//
//	public void addProducer(BlockEntity tile, double d) {
//		if (!currentProducers.contains(tile)) {
//			currentProducers.add(tile);
//		}
//		voltage = Math.max(voltage, d);
//	}
//
//	@Override
//	public void tick() {
//		super.tick();
//		if (transferBuffer > 0) {
//			if ((int) voltage != 0 && voltage > 0) {
//				if (resistance > 0) {
//					double bufferAsWatts = transferBuffer * 20; // buffer as watts
//					double maxWatts = (-voltage * voltage + voltage * Math.sqrt(voltage * voltage + 4 * bufferAsWatts * resistance)) / (2 * resistance);
//					double maxPerTick = maxWatts / 20.0;
//					// above is power as watts when powerSend + powerLossToWires = m
//					TransferPack send = TransferPack.joulesVoltage(maxPerTick, voltage);
//					double sent = sendToReceivers(send, currentProducers, false).getJoules();
//					double lossPerTick = send.getAmps() * send.getAmps() * resistance / 20.0;
//					transferBuffer -= sent + lossPerTick;
//					energyLoss += lossPerTick;
//					transmittedThisTick += lossPerTick;
//					checkForOverload();
//				} else {
//					transferBuffer -= sendToReceivers(TransferPack.joulesVoltage(transferBuffer, voltage), currentProducers, false).getJoules();
//				}
//			}
//		}
//		lastVoltage = voltage;
//		voltage = 0;
//		lastEnergyLoss = energyLoss;
//		energyLoss = 0;
//		currentProducers.clear();
//		maxTransferBuffer = 0;
//		for (BlockEntity tile : acceptorSet) {
//			if (acceptorInputMap.containsKey(tile)) {
//				for (Direction connection : acceptorInputMap.get(tile)) {
//					TransferPack pack = ElectricityUtils.receivePower(tile, connection, TransferPack.joulesVoltage(Double.MAX_VALUE, voltage), true);
//					if (pack.getJoules() != 0) {
//						maxTransferBuffer += pack.getJoules();
//						break;
//					}
//				}
//			}
//		}
//		Iterator<IMatterNetworkCable> it = conductorSet.iterator();
//		boolean broken = false;
//		while (it.hasNext()) {
//			IMatterNetworkCable conductor = it.next();
//			if (conductor instanceof BlockEntity entity && entity.isRemoved() || conductor.getNetwork() != this) {
//				broken = true;
//				break;
//			}
//		}
//		if (broken) {
//			refresh();
//		}
//		if (getSize() == 0) {
//			deregister();
//		}
//	}
//
//	@Override
//	public boolean isConductor(BlockEntity tile) {
//		return ElectricityUtils.isConductor(tile);
//	}
//
//	@Override
//	public boolean isAcceptor(BlockEntity acceptor, Direction orientation) {
//		return ElectricityUtils.isElectricReceiver(acceptor);
//	}
//
//	@Override
//	public AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack> createInstance() {
//		return new MatterNetwork();
//	}
//
//	@Override
//	public AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack> createInstanceConductor(Set<IMatterNetworkCable> conductors) {
//		return new MatterNetwork(conductors);
//	}
//
//	@Override
//	public AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack> createInstance(Set<AbstractNetwork<IMatterNetworkCable, SubtypeWire, BlockEntity, TransferPack>> networks) {
//		return new MatterNetwork(networks);
//
//	}
//
//	@Override
//	public SubtypeWire[] getConductorTypes() {
//		return SubtypeWire.values();
//	}
//
//	@Override
//	public boolean canConnect(BlockEntity acceptor, Direction orientation) {
//		return ElectricityUtils.canInputPower(acceptor, orientation.getOpposite());
//	}
//
//	@Override
//	public double getJoulesStored() {
//		return transferBuffer;
//	}
//
//	@Override
//	public void setJoulesStored(double joules) {
//		transferBuffer = joules;
//	}
//
//	@Override
//	public double getMaxJoulesStored() {
//		return maxTransferBuffer;
//	}
//}
